package com.app.autonomoustesting.features.main

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.autonomoustesting.R
import com.app.autonomoustesting.shared.broadcast.NetworkStateChangeReceiver
import com.app.autonomoustesting.shared.sharedPreferences.SharedPreferencesManager
import com.app.autonomoustesting.shared.ui.activity.BaseActivity
import com.app.autonomoustesting.shared.ui.recyclerView.OnItemClickListener
import com.app.autonomoustesting.shared.ui.recyclerView.itemDecoration.DividerItemDecoration
import com.app.autonomoustesting.shared.utils.DateUtil
import com.app.autonomoustesting.shared.utils.extensions.gone
import com.app.autonomoustesting.shared.utils.extensions.reObserve
import com.app.autonomoustesting.shared.utils.extensions.visible
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_progress.*
import javax.inject.Inject


class MainActivity : BaseActivity(){

    @Inject
    lateinit var sharedPreferencesManager: SharedPreferencesManager

    private val viewModel by viewModels<MainViewModel> { viewModelFactory }

    private var networkStateChangeReceiver: NetworkStateChangeReceiver? = null

    private var citySearchValue =""

    private val weatherAdapter: DailyWeatherAdapter by lazy {
        DailyWeatherAdapter()
    }

    private val cityAdapter: CityAutoCompleteAdapter by lazy {
        CityAutoCompleteAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainComponent.inject(this)
        setContentView(R.layout.activity_main)
        init()
    }


    override fun onResume() {
        super.onResume()
        registerNetworkStateChangeBroadcastReceiver()
    }

    override fun onPause() {
        super.onPause()
        unregisterNetworkStateChangeBroadcastReceiver()
    }

    private fun init() {
        onCheckDataStorage()
        initRecyclerView()
        setupObservers()

        /*viewModel.getCityAutoComplete()
        edtSearch.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                cityAdapter.filter(newText!!)
                return false
            }

        })*/
        edtSearch.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                citySearchValue = edtSearch.text.toString()
                performSearch()
                true
            }
            false
        }
    }

    /**
     * check date to reset all data
     */
    private fun onCheckDataStorage(){
        val currentDate = DateUtil().toSimpleString(DateUtil().getCurrentDate())
        if(currentDate != sharedPreferencesManager.mainSharedPreferences.dateSearch){
            viewModel.resetAllData()
            sharedPreferencesManager.mainSharedPreferences.dateSearch = currentDate
        }
    }

    /**
     * init recycler view
     */
    private fun initRecyclerView() {
        initAdapter()
        rcvWeather?.apply {
            setHasFixedSize(true)
            adapter = weatherAdapter
            layoutManager = LinearLayoutManager(context)
            val dividerItemDecoration = DividerItemDecoration(
                context
            )
//            addItemDecoration(dividerItemDecoration)
        }

        rcvCity?.apply {
            setHasFixedSize(true)
            adapter = cityAdapter
            layoutManager = LinearLayoutManager(context)
//            val dividerItemDecoration = DividerItemDecoration(
//                context
//            )
//            addItemDecoration(dividerItemDecoration)
        }
    }

    /**
     * init adapter click
     */
    private fun initAdapter() {
       // init addItemClickListener
        val clickListener = object : OnItemClickListener {
            override fun onItemClicked(cityName: String) {
                viewModel.getWeatherData(cityName)
            }
        }
        cityAdapter.addItemClickListener(clickListener)
    }

    /**
     * config observers of live data
     */
    private fun setupObservers() {
        viewModel.loadingLiveData.reObserve(this, loadingObserver)
        viewModel.errorLiveData.reObserve(this, errorObserver)
        viewModel.weatherModelLiveData.reObserve(this, weatherModelObserver)
        viewModel.cityModelLiveData.reObserve(this, cityModelObserver)
        viewModel.errorCityLiveData.reObserve(this, errorCityObserver)
    }

    /**
     * init weather observer
     */
    private val weatherModelObserver: Observer<ArrayList<DailyWeatherModel>> = Observer { items ->
        if (items.isEmpty()) {
            showEmptyScreen()
            tvCityDescription.text =""
            edtSearch.text.clear()
        } else {
            showContent()
            updateAdapter(items)
            tvCityDescription.text = resources.getString(R.string.city_description).format(citySearchValue)
            edtSearch.text.clear()
        }
    }

    private val loadingObserver: Observer<Boolean> = Observer { isLoading ->
        if (isLoading) {
            progressBar.visible()
        } else {
            progressBar.gone()
        }
    }

    /**
     * init error Observer
     */
    private val errorObserver: Observer<String> = Observer { error ->
        tvCityDescription.text =""
        edtSearch.text.clear()
        showEmptyScreen()
        showError(error)
    }

    /**
     * init city observer
     */
    private val cityModelObserver: Observer<ArrayList<CityModel>> = Observer { items ->
        if (items.isEmpty()) {
            showCityAutoCompleteEmptyScreen()
        } else {
            showCityAutoCompleteContent()
            updateCityAdapter(items)
        }
    }

    /**
     * show empty screen
     */
    private fun showEmptyScreen() {
        contentContainer?.gone()
        cityContainer?.gone()
        emptyScreenContainer?.visible()
    }

    private fun showContent() {
        contentContainer?.visible()
        cityContainer?.gone()
        emptyScreenContainer?.gone()
    }

    private fun updateAdapter(items: ArrayList<DailyWeatherModel>) {
        weatherAdapter.autoNotify(items)
    }

    private fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun updateCityAdapter(items: ArrayList<CityModel>) {
        cityAdapter.updateBackupData(items)
        cityAdapter.autoNotify(items)
    }

    private fun showCityAutoCompleteEmptyScreen() {
        contentContainer?.gone()
        cityContainer?.gone()
        emptyScreenContainer?.visible()
    }

    private fun showCityAutoCompleteContent() {
        cityContainer?.visible()
        contentContainer?.gone()
        emptyScreenContainer?.gone()
    }

    private val errorCityObserver: Observer<String> = Observer { error ->
        showCityAutoCompleteEmptyScreen()
        showError(error)
    }


    private fun performSearch(){
        viewModel.getWeatherData(citySearchValue)
    }

    private fun registerNetworkStateChangeBroadcastReceiver() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        networkStateChangeReceiver = NetworkStateChangeReceiver()
        registerReceiver(networkStateChangeReceiver, intentFilter)
    }

    private fun unregisterNetworkStateChangeBroadcastReceiver() {
        unregisterReceiver(networkStateChangeReceiver)
    }

}