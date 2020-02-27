package cn.rubintry.self.view.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.QMUIProgressBar;

import butterknife.BindView;
import butterknife.OnClick;
import cn.rubintry.self.R;
import cn.rubintry.self.common.base.BaseActivity;
import cn.rubintry.self.common.constants.ExtraConstants;

/**
 * @author logcat
 */
public class WebViewActivity extends BaseActivity {


    private static final String TAG = "WebViewActivity";
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.progressBar)
    QMUIProgressBar progressBar;
    @BindView(R.id.tvBarTitle)
    TextView tvBarTitle;

    private String url;

    @Override
    protected boolean lightMode() {
        return false;
    }

    @Override
    protected boolean showBackBtn() {
        return true;
    }

    @Override
    protected String setTitleString() {
        return "";
    }

    @Override
    protected int setTitleColor() {
        return Color.WHITE;
    }

    @Override
    protected int attachedLayoutRes() {
        return R.layout.activity_web_view;
    }

    @Override
    protected int setTopBarBackground() {
        return 0;
    }

    @Override
    protected int setBackBtnBackground() {
        return Color.WHITE;
    }

    @Override
    protected void initViews() {
        super.initViews();
        url = getIntent().getStringExtra(ExtraConstants.URL);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setSupportZoom(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCachePath(ContextCompat.getExternalCacheDirs(this)[0].getPath());
        //隐藏缩放控制条
        webSettings.setBuiltInZoomControls(false);
        // android 5.0以上默认不支持Mixed Content
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        //按照API的说明  Sets whether the DOM storage API is enabled. The default value is false. 
        //
        //也就是是否开启本地DOM存储。应该是Html 5中的localStorage(可以使用Android4.4手机和Chrome Inspcet Device联调)，
        // 用于持久化的本地存储，除非主动删除数据，否则数据是永远不会过期的，绝大多数的浏览器都是支持 localStorage 的，
        // 但是鉴于它的安全特性（任何人都能读取到它，尽管有相应的限制，将敏感数据存储在这里依然不是明智之举），Android
        // 默认是关闭该功能的。
        webSettings.setDomStorageEnabled(true);
        webView.loadUrl(url);
        webView.setWebViewClient(webViewClient);
        webView.setWebChromeClient(webChromeClient);
    }

    @Override
    protected void requestData() {
        super.requestData();
    }

    @Override
    public void onNext(Object o, int requestCode) {
        super.onNext(o, requestCode);
    }

    @Override
    public void onError(Throwable e, int request) {
        super.onError(e, request);
    }

    private WebViewClient webViewClient = new WebViewClient() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            Log.d(TAG, "访问网址: " + request.getUrl());
            //http和https协议需跳转到网页里
            if(request.getUrl().toString().startsWith("http:") || request.getUrl().toString().startsWith("https:")){
                webView.loadUrl(request.getUrl().toString());
            }else{
                //其他协议则跳转到对应的activity里
                openActivity(request.getUrl().toString());
            }
            return true;
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

    };

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }else{
            super.onBackPressed();
        }
    }

    private WebChromeClient webChromeClient = new WebChromeClient(){
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if(newProgress == 100){
                tvBarTitle.setText(view.getTitle());
                progressBar.setVisibility(View.GONE);
            }else{
                progressBar.setVisibility(View.VISIBLE);
            }
            progressBar.setProgress(newProgress);
            super.onProgressChanged(view, newProgress);
        }
    };

    /**
     * 根据url打开对应的activity
     * @param url
     */
    private void openActivity(String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW , Uri.parse(url));
            startActivity(intent);
        }catch (Exception e){
            Log.e(TAG, "openActivity: ",e );
        }
    }

    @OnClick({R.id.imgToolbarBack})
    void onClick(View view){
        switch (view.getId()){
            case R.id.imgToolbarBack:
                if(webView.canGoBack()){
                    webView.goBack();
                }else{
                    finish();
                }
                break;
                default:
                    break;
        }
    }
}
