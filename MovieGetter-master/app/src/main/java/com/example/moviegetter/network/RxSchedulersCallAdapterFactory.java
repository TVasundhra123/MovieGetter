package com.example.moviegetter.network;

import androidx.annotation.NonNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RxSchedulersCallAdapterFactory extends CallAdapter.Factory {
    private final SchedulersProvider schedulersProvider;
    private final RxJava2CallAdapterFactory adapterFactory;
    private final boolean observeOnMainThread;

    public RxSchedulersCallAdapterFactory(SchedulersProvider schedulersProvider) {
        this(schedulersProvider, true);
    }

    public RxSchedulersCallAdapterFactory(SchedulersProvider schedulersProvider, boolean observeOnMainThread) {
        this.schedulersProvider = schedulersProvider;
        this.adapterFactory = RxJava2CallAdapterFactory
                .createWithScheduler(schedulersProvider.getIo());
        this.observeOnMainThread = observeOnMainThread;
    }

    @Override
    public CallAdapter<?, ?> get(@NonNull Type returnType, @NonNull Annotation[] annotations, @NonNull Retrofit retrofit) {
        final CallAdapter callAdapter = adapterFactory.get(returnType, annotations, retrofit);

        return new CallAdapter<Single<?>, Single<?>>() {
            @Override
            public Type responseType() {
                return callAdapter.responseType();
            }

            @Override
            public Single adapt(@NonNull Call<Single<?>> call) {
                if (observeOnMainThread) {
                    return ((Single) callAdapter.adapt(call)).observeOn(schedulersProvider.getUi());
                } else {
                    return ((Single) callAdapter.adapt(call));
                }
            }
        };
    }
}
