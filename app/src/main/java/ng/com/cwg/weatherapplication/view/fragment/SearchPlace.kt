package ng.com.cwg.weatherapplication.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import ng.com.cwg.weatherapplication.R
import ng.com.cwg.weatherapplication.viewmodel.SearchPlaceViewModel

class SearchPlace : Fragment() {

    companion object {
        fun newInstance() = SearchPlace()
    }

    private lateinit var viewModel: SearchPlaceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_place_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SearchPlaceViewModel::class.java)
        // TODO: Use the ViewModel
    }

}