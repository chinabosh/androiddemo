package com.bosh.module_mvp.constant;

/**
 * @author lzq
 * @date 2018/9/5
 */

public class EventCode {

    public static final String AMAP_SERVICE_ADD = "amap_service_add";
    public static final String AMAP_SERVICE_DELETE = "amap_service_delete";
    public static final String AMAP_SERVICE_UPDATE = "amap_service_update";
    public static final String AMAP_SERVICE_LIST = "amap_service_list";

    public static final String LOGIN = "login";
    public static final String LOGOUT = "logout";
    public static final String MODIFY_PWD = "modify_pwd";
    public static final String GET_SHORT = "get_short";
    public static final String VERIFY_SHORT = "verify_short";
    public static final String SET_NEW_PWD = "set_new_pwd";


    public static final String GET_PRICE_LIST = "get_price_list";//查看价目清单
    public static final String GET_PRICE_LIST_REFRESH = "get_price_list_refresh";//更新价目清单
    public static final String GET_PAY_ITEM_TYPE = "get_pay_item_type";//获取价目清单类别
    public static final String GET_PRICE_LIST_FROM_DB = "get_price_list_from_db";//数据库中获取价目清单
    public static final String SAVE_PRICE_LIST_TO_DB = "save_price_list_to_db";
    public static final String GET_CAR_INFO = "get_car_info";//查看当前出车信息
    public static final String GET_CUR_ORDER = "get_cur_order";//查看当前委派单
    public static final String GET_NO_PAY_ORDER = "get_no_pay_order";//查看未支付委派单
    public static final String GET_HISTORY_ORDER = "get_history_order";//查看历史委派单
    public static final String GET_ORDER_LIST = "get_order_list";//查看委派单列表
    public static final String GET_ORDER_DETAIL = "get_order_detail";//查看委派单所有详情
    public static final String UPDATE_ADDRESS = "update_address";//更新委派单实际地址
    public static final String ADD_PATIENT = "add_patient";//委派单添加患者信息
    public static final String MODIFY_PATIENT = "modify_patient";//委派单修改患者信息
    public static final String MODIFY_PATIENT_ALL = "modify_patient_all";//委派单修改多个患者信息
    public static final String DELETE_PATIENT = "delete_patient";//委派单删除患者信息
    public static final String UPDATE_RECORD_STATE = "update_record_state";//更改委派单状态
    public static final String UPDATE_PATIENT_STATE = "update_patient_state";//更改委派单患者状态
    public static final String UPDATE_PAY_INFO = "update_pay_info";//记录单保存患者信息和费用清单
    public static final String GET_BASE_SETTING = "get_base_setting";//获取基础配置
    public static final String GET_STATISTICS = "get_statistics";//救治计算数据统计
    public static final String GET_SETTLE_LIST = "get_settle_list";//结算统计列表
    public static final String DEAL_PAY_LIST = "deal_pay_list";//收费信息特殊处理
    public static final String GET_PATIENT_DETAIL = "get_patient_detail";//获取患者详细信息
    public static final String GET_DIAGNOSE = "get_diagnose";//获取诊断列表
    public static final String GET_DIAGNOSE_TYPE = "get_diagnose_type";//获取诊断大类
    public static final String SEARCH_DIAGNOSE = "search_diagnose";//根据诊断名称查询诊断
    public static final String MODIFY_HISTORY = "modify_history";//修改委派单信息

    /* 结算 */
    public static final String GET_QR_CODE = "get_qr_code";//获取二维码连接
    public static final String SAVE_SETTLE_ORDER = "save_settle_order";//支付订单保存
    public static final String CANCEL_SETTLE_ORDER = "cancel_settle_order";//取消支付订单
    public static final String GET_PAY_RESULT = "get_pay_result";//支付结果轮询
    public static final String SAVE_CASH = "save_crash";//费用结算
    public static final String CANCEL_SAVE_CASH = "cancel_save_cash";//取消结算
    public static final String SAVE_PAY_RECORD = "save_pay_record";//缴费记录保存
    public static final String GET_SETTLEMENT_NUM = "app?act=settlementGetNum";//获取结算单号

    public static final String STOP_TRACK = "stop_track";//停止定位
    public static final String GET_DISTANCE = "get_distance";//计算行驶里程
    public static final String HISTORY_TRACK = "history_track";

    public static final String EVENT_LOGIN_OVERTIME = "event_login_overtime";
    public static final String REFRESH_ORDER_INFO = "refresh_order_info";//委派单信息更新
    public static final String REFRESH_CUR_PAGE = "refresh_cur_page";
    public static final String REFRESH_CUR_PAGE_NOW = "refresh_cur_page_now";//立即刷新当前委派单

    public static final String GET_HOSPITAL = "get_hospital";//获取医院列表
    public static final String UPLOAD_SIGN = "upload_sign";//上传医护驾签名

    public static final String DOWNLOAD_APK_PROGRESS = "download_apk_progress";
    public static final String CHECK_VERSION = "check_version";
}
