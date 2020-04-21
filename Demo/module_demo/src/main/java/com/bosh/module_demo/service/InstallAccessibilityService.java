package com.bosh.module_demo.service;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.china.bosh.mylibrary.annotation.Open;

/**
 * @author bosh
 */
@Open
public class InstallAccessibilityService extends AccessibilityService {
    SparseBooleanArray handledMap = new SparseBooleanArray();

    public InstallAccessibilityService() {
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.e("Test", "onAccessibilityEvent");
        AccessibilityNodeInfo nodeInfo = event.getSource();
        if (nodeInfo != null) {
            int eventType = event.getEventType();
            if (eventType== AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED ||
                    eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
                if (!handledMap.get(event.getWindowId())) {
                    boolean handled = iterateNodesAndHandle(nodeInfo);
                    if (handled) {
                        handledMap.put(event.getWindowId(), true);
                    }
                }
            }
        }
    }

    private boolean iterateNodesAndHandle(AccessibilityNodeInfo nodeInfo) {
        if (nodeInfo != null) {
            int childCount = nodeInfo.getChildCount();
            if ("android.widget.Button".contentEquals(nodeInfo.getClassName())) {
                String nodeContent = nodeInfo.getText().toString();
                Log.d("TAG", "content is " + nodeContent);
                if ("安装".equals(nodeContent)
//                        || "完成".equals(nodeContent)
                        || "确定".equals(nodeContent)
                        || "确认".equals(nodeContent)
                        || "打开".equals(nodeContent)) {
                    nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    return true;
                }
            } else if ("android.widget.ScrollView".contentEquals(nodeInfo.getClassName())) {
                nodeInfo.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
            }
            for (int i = 0; i < childCount; i++) {
                AccessibilityNodeInfo childNodeInfo = nodeInfo.getChild(i);
                if (iterateNodesAndHandle(childNodeInfo)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onInterrupt() {

    }
}
