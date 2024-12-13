package com.moreira.techpoint.dtos;

public class UpdateTimeBankDTO {

    private boolean updateLunchOut;
    private boolean updateLunchIn;
    private boolean updateClockOut;

    public UpdateTimeBankDTO(boolean updateLunchOut, boolean updateLunchIn, boolean updateClockOut) {
        this.updateLunchOut = updateLunchOut;
        this.updateLunchIn = updateLunchIn;
        this.updateClockOut = updateClockOut;
    }

    public boolean isUpdateLunchOut() {
        return updateLunchOut;
    }

    public boolean isUpdateLunchIn() {
        return updateLunchIn;
    }

    public boolean isUpdateClockOut() {
        return updateClockOut;
    }
}
