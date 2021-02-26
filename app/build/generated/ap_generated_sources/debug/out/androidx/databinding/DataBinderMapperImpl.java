package androidx.databinding;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
  DataBinderMapperImpl() {
    addMapper(new ng.com.cwg.weatherapplication.DataBinderMapperImpl());
  }
}
