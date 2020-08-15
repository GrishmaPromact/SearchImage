package com.grishma.searchimage.ui

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.grishma.searchimage.R
import com.grishma.searchimage.adapter.ImageSearchListAdapter
import com.grishma.searchimage.model.ImageSearch
import com.grishma.searchimage.utils.Utilities
import com.grishma.searchimage.viewmodel.ImageSearchViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit


/**
 * MainActivity
 */

class MainActivity : AppCompatActivity() {

    private var imageSearchViewModel: ImageSearchViewModel? = null
    private lateinit var subject: PublishSubject<String>

    private val imageSearchPageListAdapter = ImageSearchListAdapter(mutableListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageSearchViewModel =
            ViewModelProvider(this).get(ImageSearchViewModel::class.java)
        subject = PublishSubject.create()

        observeSearchView()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String?): Boolean {
                //subject.onComplete()
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(text: String): Boolean {
                subject.onNext(text)

                return true
            }
        })


        initializeList()
    }

    private fun initializeList() {

        var layoutManager: GridLayoutManager =
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                GridLayoutManager(this, 3)
            } else {
                GridLayoutManager(this, 5)
            }
        rvImages.apply {
            rvImages.layoutManager = layoutManager
            rvImages.adapter = imageSearchPageListAdapter
        }

        observeDataChange()
    }


    //observe SearchView
    private fun observeSearchView() {

        subject.debounce(1000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subject<String?>() {

                override fun subscribeActual(observer: io.reactivex.Observer<in String?>?) {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }

                override fun getThrowable(): Throwable? {
                    return null;
                }

                override fun hasThrowable(): Boolean {
                    return false
                }

                override fun hasObservers(): Boolean {
                    return false
                }

                override fun onComplete() {
                }

                override fun hasComplete(): Boolean {
                    return false
                }

                override fun onNext(t: String) {

                    if (Utilities.isNetworkConnected(this@MainActivity)) {
                        //api call to get images
                        imageSearchViewModel?.getAllImages("Client-ID 137cda6b5008a7c", t)
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            getString(R.string.internet_error),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
    }


    //observer data change
    private fun observeDataChange() {
        //observe error
        imageSearchViewModel?.apiError()?.observe(
            this, Observer {
                Toast.makeText(this, getString(R.string.some_error), Toast.LENGTH_SHORT).show()
            }
        )

        //observe progressbar
        imageSearchViewModel?.loading()?.observe(
            this, Observer {
                if (it!!) progressBar.visibility == View.VISIBLE else progressBar.visibility == View.GONE
            })

        //observe api data
        imageSearchViewModel?.apiImageSearchData()?.observe(this, Observer {
            searchView.clearFocus()
            imageSearchPageListAdapter.addImagesList(it.second.data)
        })

    }

    //onActivityResult
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Utilities.LAUNCH_SECOND_ACTIVITY) if (resultCode == Activity.RESULT_OK) {

            //get data from intent
            val empData : ImageSearch.Data = data?.getSerializableExtra(Utilities.RESULT) as ImageSearch.Data
            val position : Int? =  data.extras?.getInt(Utilities.POSITION)

            //update adapter item
            imageSearchPageListAdapter.updateItem(empData,position)

            Toast.makeText(
                this@MainActivity, "Update Employee item id : $position", Toast.LENGTH_LONG).show()

        }
    }
}