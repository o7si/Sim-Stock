package cn.o7si.sim;

public class MarketManager extends Thread {

    private Integer interval;

    private Boolean runState;

    @Override
    public void run() {
        // 循环模拟市场变化
        while (runState) {
            MarketSim.simulate();
            try {
                // 每隔固定时长模拟一次
                sleep(interval);
            } catch (Exception ignored) {
                // 忽略异常
            }
        }
    }

    public MarketManager(Integer interval) {
        this.interval = interval;
        this.runState = true;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Boolean getRunState() {
        return runState;
    }

    public void setRunState(Boolean runState) {
        this.runState = runState;
    }
}
