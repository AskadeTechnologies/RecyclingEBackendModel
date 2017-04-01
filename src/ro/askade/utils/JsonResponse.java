package ro.askade.utils;

/**
 * Created by AdrianIonita on 1/27/2017.
 */

public class JsonResponse {
    public static final String STATUS_SUCCESS = "SUCCESS";
    public static final String STATUS_ERROR = "ERROR";

    private String status;
    private String statusMessage;
    private Object data;

    private JsonResponse(String status) {
        this.status = status;
    }

    /**
     * @return
     */
    public static JsonResponse forSuccess() {
        return new JsonResponse(STATUS_SUCCESS);
    }

    /**
     * @param data
     * @return
     */
    public static JsonResponse forSuccess(Object data) {
        JsonResponse jr = forSuccess();
        jr.setData(data);
        return jr;
    }

    /**
     * @param message
     * @param data
     * @return
     */
    public static JsonResponse forSuccess(String message, Object data) {
        JsonResponse jr = forSuccess();
        jr.setData(data);
        jr.statusMessage = message;
        return jr;
    }

    /**
     * @param errorMessage
     * @return
     */
    public static JsonResponse forError(String errorMessage) {
        JsonResponse jr = new JsonResponse(STATUS_ERROR);
        jr.statusMessage = errorMessage;
        return jr;
    }

    /**
     * @param errorMessage
     * @param data
     * @return
     */
    public static JsonResponse forError(String errorMessage, Object data) {
        JsonResponse jr = forError(errorMessage);
        jr.setData(data);
        return jr;
    }

    /**
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     * @return
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * @return
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data
     */
    public void setData(Object data) {
        this.data = data;
    }
}

