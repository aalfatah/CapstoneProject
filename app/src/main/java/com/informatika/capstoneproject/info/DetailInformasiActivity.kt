package com.informatika.capstoneproject.info

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.informatika.capstoneproject.R
import com.informatika.capstoneproject.model.ModelArticle
import kotlinx.android.synthetic.main.activity_detail_informasi.*

class DetailInformasiActivity : AppCompatActivity() {

    companion object {
        const val DETAIL_NEWS = "DETAIL_NEWS"
    }

    var modelArticle: ModelArticle? = null
    var strNewsURL: String? = null
    var strTitle: String? = null
    var strSubTitle: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_informasi)

        setSupportActionBar(toolbar)
        assert(supportActionBar != null)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        progressBar.max = 100

        //get data intent
        modelArticle = intent.getParcelableExtra(DETAIL_NEWS)
        if (modelArticle != null) {

            strNewsURL = modelArticle?.url
            strTitle = modelArticle?.title
            strSubTitle = modelArticle?.url

            tvTitle.text = strTitle
            tvSubTitle.text = strSubTitle

            //share news
            imgShare.setOnClickListener {
                val share = Intent(Intent.ACTION_SEND)
                share.type = "text/plain"
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
                share.putExtra(Intent.EXTRA_TEXT, strNewsURL)
                startActivity(Intent.createChooser(share, "Bagikan ke : "))
            }

            //show news
            showWebView()
        }
    }

    private fun showWebView() {
        webView.settings.loadsImagesAutomatically = true
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.setSupportZoom(true)
        webView.settings.builtInZoomControls = true
        webView.settings.displayZoomControls = false
        webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        webView.loadUrl(strNewsURL!!)

        progressBar.progress = 0

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, newUrl: String): Boolean {
                view.loadUrl(newUrl)
                progressBar.progress = 0
                return true
            }

            override fun onPageStarted(view: WebView, urlStart: String, favicon: Bitmap?) {
                super.onPageStarted(view, urlStart, favicon)
                strNewsURL = urlStart
                invalidateOptionsMenu()
            }

            override fun onPageFinished(view: WebView, urlPage: String) {
                super.onPageFinished(view, urlPage)
                progressBar.visibility = View.GONE
                invalidateOptionsMenu()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}