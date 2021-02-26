package ng.com.cwg.weatherapplication.databinding;
import ng.com.cwg.weatherapplication.R;
import ng.com.cwg.weatherapplication.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class HomeFragmentBindingImpl extends HomeFragmentBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.topRelativeLayout, 1);
        sViewsWithIds.put(R.id.searchPlaces, 2);
        sViewsWithIds.put(R.id.placeInfo, 3);
        sViewsWithIds.put(R.id.temperatureTextView, 4);
        sViewsWithIds.put(R.id.weatherStatusTextView, 5);
        sViewsWithIds.put(R.id.tempStatusLV, 6);
        sViewsWithIds.put(R.id.minTempTextView, 7);
        sViewsWithIds.put(R.id.currentTempTextView, 8);
        sViewsWithIds.put(R.id.maxTempTextView, 9);
        sViewsWithIds.put(R.id.divider, 10);
        sViewsWithIds.put(R.id.recyclerView, 11);
        sViewsWithIds.put(R.id.addToFavorite, 12);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public HomeFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }
    private HomeFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[12]
            , (android.widget.TextView) bindings[8]
            , (android.view.View) bindings[10]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[7]
            , (android.widget.LinearLayout) bindings[0]
            , (android.widget.TextView) bindings[3]
            , (androidx.recyclerview.widget.RecyclerView) bindings[11]
            , (android.widget.Button) bindings[2]
            , (android.widget.LinearLayout) bindings[6]
            , (android.widget.TextView) bindings[4]
            , (android.widget.RelativeLayout) bindings[1]
            , (android.widget.TextView) bindings[5]
            );
        this.parentLayout.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}