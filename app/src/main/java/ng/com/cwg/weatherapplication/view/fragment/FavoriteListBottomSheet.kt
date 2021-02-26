package ng.com.cwg.weatherapplication.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.persistent_bottom_sheet_selector.*
import ng.com.cwg.weatherapplication.viewmodel.FavoritesViewModel
import ng.com.cwg.weatherapplication.R
import ng.com.cwg.weatherapplication.adapters.FavoritesRvAdapter
import ng.com.cwg.weatherapplication.db.entity.SaveWeatherInfoEntity
import java.util.*

/**
 * @author .: Ossai Michael
 * @email ..: mikeossaiofficial@gmail.com, michael.ossai@cwg-plc.com
 * @created : 05/10/2020 07:11
 */
class FavoriteListBottomSheet: BottomSheetDialogFragment(){
    private lateinit var viewModel: FavoritesViewModel
    private lateinit var adapter : FavoritesRvAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, R.style.AppBottomSheetDialogTheme)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.persistent_bottom_sheet_selector, container, false)
        viewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)
        return v
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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