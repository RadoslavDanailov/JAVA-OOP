package fairyShop.models;

public class ShopImpl implements Shop {

    @Override
    public void craft(Present present, Helper helper) {

        boolean isDone = false;
        int brokenInstruments = 0;
        while (helper.canWork() && !isDone) {
            for (Instrument instrument : helper.getInstruments()) {
                if (present.isDone()) {
                    break;
                }
                while (!instrument.isBroken() && helper.canWork()) {
                    helper.work();
                    instrument.use();
                    if (instrument.isBroken()) {
                        brokenInstruments++;
                    }
                    present.getCrafted();
                    if (present.isDone()) {
                        isDone = true;
                        break;
                    }
                }
            }
        }
    }
}
