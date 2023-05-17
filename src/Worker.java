public class Worker {

    private OnTaskDoneListener callback;
    private OnTaskErrorListener errorCallback;

    public Worker(OnTaskDoneListener callback,
                  OnTaskErrorListener errorCallback) {
        this.callback = callback;
        this.errorCallback = errorCallback;
    }

    public void start() {
        for (int i = 0; i < 100; i++) {
            if (i == 33) {
                try {
                    throw new Exception("Ошибка выполнения");
                } catch (Exception e) {
                    errorCallback.onError("Task " + i + " " + e.getMessage());
                }
            } else {
                callback.onDone("Task " + i + " is done");
            }
        }
    }

    @FunctionalInterface
    public interface OnTaskDoneListener {
        void onDone(String result);
    }

    public interface OnTaskErrorListener {
        void onError(String message);
    }
}
