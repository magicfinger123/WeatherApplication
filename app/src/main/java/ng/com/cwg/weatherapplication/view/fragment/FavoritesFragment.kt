package ng.com.cwg.weatherapplication.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.home_fragment.*
import ng.com.cwg.weatherapplication.viewmodel.FavoritesViewModel
import ng.com.cwg.weatherapplication.R
import ng.com.cwg.weatherapplication.adapters.FavoritesRvAdapter
import ng.com.cwg.weatherapplication.databinding.FavoritesFragmentBinding
import ng.com.cwg.weatherapplication.db.entity.SaveWeatherInfoEntity
import java.util.*

class FavoritesFragment : Fragment() {
    private lateinit var viewModel: FavoritesViewModel
    private lateinit var binding:FavoritesFragmentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorites_fragment, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)
        init()
    }
    private fun init(){
        viewModel.getFavorites()
        viewModel.observeFavorites().observe(viewLifecycleOwner,{
            recyclerView.layoutManager = LinearLayoutManager(context)
            val adapter: FavoritesRvAdapter = FavoritesRvAdapter(requireContext(), it as ArrayList<SaveWeatherInfoEntity>?
            )
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        })
    }

}