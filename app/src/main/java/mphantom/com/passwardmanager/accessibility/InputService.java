package mphantom.com.passwardmanager.accessibility;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * Created by wushaorong on 16-8-22.
 */

public class InputService extends AccessibilityService {
    public final static String TAG = InputService.class.getName();

    private static String classname;
    private static String mForegroundPackageName;
    private static InputService mInstance = null;

    public InputService() {
    }

    public static InputService getInstance() {
        if (mInstance == null) {
            synchronized (InputService.class) {
                if (mInstance == null) {
                    mInstance = new InputService();
                }
            }
        }
        return mInstance;
    }

    /**
     * 监听窗口焦点,并且获取焦点窗口的包名
     *
     * @param event
     */
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            mForegroundPackageName = event.getPackageName().toString();
            classname = event.getClassName().toString();
            Log.d("TestAsceee", "**方法五** 当前窗口焦点对应的包名为： =" + mForegroundPackageName);
            Log.d("TestAsceee", "**方法五** 当前窗口焦点对应的类名为： =" + classname);
            if (classname.equals("mphantom.com.trytestrealm.MainActivity")) {
                getWindowChange();
            }
        }
    }

    @Override
    public void onInterrupt() {
    }

    public String getForegroundPackage() {
        return mForegroundPackageName;
    }

    public String getForegroundClassName() {
        return classname;
    }

    public void getWindowChange() {
        AccessibilityNodeInfo nodeInfo = this.getRootInActiveWindow();
        AccessibilityNodeInfo targetNode = null;
        targetNode = findNode(nodeInfo, "Hello World!");
        if (targetNode.isClickable()) {
            targetNode.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        }
    }

    private AccessibilityNodeInfo findNode(AccessibilityNodeInfo node, String text) {
        List<AccessibilityNodeInfo> list = node.findAccessibilityNodeInfosByText(text);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 此方法用来判断当前应用的辅助功能服务是否开启
     *
     * @param context
     * @return
     */
    public static boolean isAccessibilitySettingsOn(Context context) {
        int accessibilityEnabled = 0;
        try {
            accessibilityEnabled = Settings.Secure.getInt(context.getContentResolver(),
                    Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {
            Log.d(TAG, e.getMessage());
        }

        if (accessibilityEnabled == 1) {
            String services = Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (services != null) {
                return services.toLowerCase().contains(context.getPackageName().toLowerCase());
            }
        }
        return false;
    }
}
