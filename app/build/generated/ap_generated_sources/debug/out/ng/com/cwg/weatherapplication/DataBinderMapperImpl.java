package ng.com.cwg.weatherapplication;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ng.com.cwg.weatherapplication.databinding.FavoritesFragmentBindingImpl;
import ng.com.cwg.weatherapplication.databinding.HomeFragmentBindingImpl;
import ng.com.cwg.weatherapplication.databinding.SearchPlaceFragmentBindingImpl;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_FAVORITESFRAGMENT = 1;

  private static final int LAYOUT_HOMEFRAGMENT = 2;

  private static final int LAYOUT_SEARCHPLACEFRAGMENT = 3;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(3);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(ng.com.cwg.weatherapplication.R.layout.favorites_fragment, LAYOUT_FAVORITESFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ng.com.cwg.weatherapplication.R.layout.home_fragment, LAYOUT_HOMEFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ng.com.cwg.weatherapplication.R.layout.search_place_fragment, LAYOUT_SEARCHPLACEFRAGMENT);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_FAVORITESFRAGMENT: {
          if ("layout/favorites_fragment_0".equals(tag)) {
            return new FavoritesFragmentBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for favorites_fragment is invalid. Received: " + tag);
        }
        case  LAYOUT_HOMEFRAGMENT: {
          if ("layout/home_fragment_0".equals(tag)) {
            return new HomeFragmentBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for home_fragment is invalid. Received: " + tag);
        }
        case  LAYOUT_SEARCHPLACEFRAGMENT: {
          if ("layout/search_place_fragment_0".equals(tag)) {
            return new SearchPlaceFragmentBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for search_place_fragment is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(1);

    static {
      sKeys.put(0, "_all");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(3);

    static {
      sKeys.put("layout/favorites_fragment_0", ng.com.cwg.weatherapplication.R.layout.favorites_fragment);
      sKeys.put("layout/home_fragment_0", ng.com.cwg.weatherapplication.R.layout.home_fragment);
      sKeys.put("layout/search_place_fragment_0", ng.com.cwg.weatherapplication.R.layout.search_place_fragment);
    }
  }
}
