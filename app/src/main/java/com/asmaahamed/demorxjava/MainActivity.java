package com.asmaahamed.demorxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func0;

public  class MainActivity extends AppCompatActivity {

    Button Run ;
    Movie movie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Run=findViewById(R.id.run_button);
        Run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                demoObservableCreate();




            }
        });
    }

    private void demoObservableCreate() {

        Observable.create(new Observable.OnSubscribe<Integer>() {


            @Override
            public void call(Subscriber<? super Integer> subscriber) {

                subscriber.onNext(1);
                subscriber.onNext(2);
                subscriber.onNext(3);
                subscriber.onCompleted();

            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
              Log.i("OnNext",String.valueOf(integer));
            }
        });
        }

    private void demoObservableInterval() {

        Observable.interval(2, TimeUnit.NANOSECONDS.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {

                Log.i("onNext",String.valueOf(aLong));
            }
        });
    }

    private void demoObservableDefer() {
        movie =new Movie("fast & fatuoi8");
             Observable<Movie>movieObservable= Observable.defer(new Func0<Observable<Movie>>() {
                 @Override
                 public Observable<Movie> call() {
                     return  Observable.just(movie);
                 }
             });

             movie = new Movie("amreka sheka beka");
             movieObservable.subscribe(new Observer<Movie>() {
                 @Override
                 public void onCompleted() {

                 }

                 @Override
                 public void onError(Throwable e) {

                 }

                 @Override
                 public void onNext(Movie movie) {

                     Log.i("onNext",String.valueOf(movie));
                 }
             });



    }

    public class Movie {
        String Name ;

        public Movie(String name) {
            Name = name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getName() {
            return Name;
        }
    }

    private void demoObservableJust() {

        Observable.just(1,2,3).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {

                Log.i("OnNext",String.valueOf(integer));
            }
        });

    }


    private  void demoObservableFrom() {
        Observable.from(new Integer[]{1,2,3}).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {

                Log.i("OnNext",String.valueOf(integer));
            }
        });

}
}