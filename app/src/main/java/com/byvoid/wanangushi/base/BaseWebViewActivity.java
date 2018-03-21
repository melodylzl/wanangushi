package com.byvoid.wanangushi.base;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.byvoid.wanangushi.utils.LogUtils;

/**
 * @author melody
 * @date 2018/2/9
 */
public class BaseWebViewActivity extends BaseActivity{

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void findView() {
        super.findView();
    }

    @Override
    protected void bindData() {
        super.bindData();
        initWebView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    protected void initWebView(){
        if (mWebView == null){
            return;
        }
        mWebView.setVerticalFadingEdgeEnabled(true);
        mWebView.setHorizontalScrollBarEnabled(true);
        mWebView.setWebViewClient(new BaseWebViewClient());
        mWebView.setWebChromeClient(new BaseWebChromeClient());
        mWebView.addJavascriptInterface(new JsInterface(),"jsInterface");

        WebSettings webSettings = mWebView.getSettings();

        // 设置是否自动加载图片，包括本地图片和网络图片，4.4以下系统设置false后，必须在onPageFinished设回true,不然加载不出图片
        // 4.4以上系统由于在onPageFinished设回true,会导致相同src的img标签中加载其中一个，所以要设回true
        webSettings.setLoadsImagesAutomatically(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT);

        // 设置是否阻止加载网络图片
        webSettings.setBlockNetworkImage(true);

        // 启用JavaScript支持
        webSettings.setJavaScriptEnabled(true);

        // 支持通过JS打开新窗口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        //支持缩放
        webSettings.setSupportZoom(false);

        // 开启DOM存储
        webSettings.setDomStorageEnabled(true);

        // 允许访问文件，Assets和resources目录下的文件不受此影响，只影响文件系统的访问
        webSettings.setAllowFileAccess(true);

        // 允许运行在一个URL环境中的JavaScript访问来自其他URL环境的内容
        webSettings.setAllowUniversalAccessFromFileURLs(true);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyWebView();
    }

    protected void destroyWebView(){
        ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView();
        viewGroup.removeAllViews();
        if (mWebView != null){
            mWebView.getSettings().setBuiltInZoomControls(true);
            mWebView.destroyDrawingCache();
            mWebView.stopLoading();
            mWebView.clearHistory();
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }

    public static class BaseWebViewClient extends WebViewClient{


        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (!view.getSettings().getLoadsImagesAutomatically()){
                view.getSettings().setLoadsImagesAutomatically(true);
            }
            view.getSettings().setBlockNetworkImage(false);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }


    public static class BaseWebChromeClient extends WebChromeClient{

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }
    }

    public static class JsInterface {

        @JavascriptInterface
        public void setResult(String result){
            LogUtils.d("jsInterface",result);
        }

    }

}
