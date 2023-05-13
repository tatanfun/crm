package crm.commons.domain;

public class ReturnObject {
    //处理成功获取失败的标记：1---成功，0---失败
    private String code;

    //提示信息
    private String message;

    //其它数据
    private Object retData;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getRetData() {
        return retData;
    }

    public void setRetData(Object retData) {
        this.retData = retData;
    }

}
