package com.example.android.aegis_proto5.Model;

public class User {
    private String noSpk;
    private String salesName;
    private String salesPhone;
    private String custName;
    private String custPhone;
    private String pickDate;
    private String selectedBranch;
    private String totalQty;
    private String totalTandaJadi;
    private String jenisKendaraan;
    private String luckyDip;
    private String jenisPembayaran;
    private String warnaKendaraan;
    private String selectedShift;

    public User() {
        this.noSpk = noSpk;
        this.salesName = salesName;
        this.salesPhone = salesPhone;
        this.custName = custName;
        this.custPhone = custPhone;
        this.pickDate = pickDate;
        this.selectedBranch = selectedBranch;
        this.totalQty = totalQty;
        this.totalTandaJadi = totalTandaJadi;
        this.jenisKendaraan = jenisKendaraan;
        this.luckyDip = luckyDip;
        this.jenisPembayaran = jenisPembayaran;
        this.warnaKendaraan = warnaKendaraan;
        this.selectedShift = selectedShift;
    }

    public User(String noSpk,
                String salesName,
                String salesPhone,
                String custName,
                String custPhone,
                String pickDate,
                String selectedBranch,
                String totalQty,
                String totalTandaJadi,
                String jenisKendaraan,
                String luckyDip,
                String jenisPembayaran,
                String warnaKendaraan,
                String selectedShift){
        this.noSpk = noSpk;
        this.salesName = salesName;
        this.salesPhone = salesPhone;
        this.custName = custName;
        this.custPhone = custPhone;
        this.pickDate = pickDate;
        this.selectedBranch = selectedBranch;
        this.totalQty = totalQty;
        this.totalTandaJadi = totalTandaJadi;
        this.jenisKendaraan = jenisKendaraan;
        this.luckyDip = luckyDip;
        this.jenisPembayaran = jenisPembayaran;
        this.warnaKendaraan = warnaKendaraan;
        this.selectedShift = selectedShift;
    }

    public String getNoSpk() {
        return noSpk;
    }

    public void setNoSpk(String noSpk) {
        this.noSpk = noSpk;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public String getSalesPhone() {
        return salesPhone;
    }

    public void setSalesPhone(String salesPhone) {
        this.salesPhone = salesPhone;
    }

    public String getPickDate() {
        return pickDate;
    }

    public void setPickDate(String pickDate) {
        this.pickDate = pickDate;
    }

    public String getSelectedBranch() {
        return selectedBranch;
    }

    public void setSelectedBranch(String selectedBranch) {
        this.selectedBranch = selectedBranch;
    }

    public String getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(String totalQty) {
        this.totalQty = totalQty;
    }

    public String getTotalTandaJadi() {
        return totalTandaJadi;
    }

    public void setTotalTandaJadi(String totalTandaJadi) {
        this.totalTandaJadi = totalTandaJadi;
    }

    public String getJenisKendaraan() {
        return jenisKendaraan;
    }

    public void setJenisKendaraan(String jenisKendaraan) {
        this.jenisKendaraan = jenisKendaraan;
    }

    public String getJenisPembayaran() {
        return jenisPembayaran;
    }

    public void setJenisPembayaran(String jenisPembayaran) {
        this.jenisPembayaran = jenisPembayaran;
    }

    public String getWarnaKendaraan() {
        return warnaKendaraan;
    }

    public void setWarnaKendaraan(String warnaKendaraan) {
        this.warnaKendaraan = warnaKendaraan;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getSelectedShift() {
        return selectedShift;
    }

    public void setSelectedShift(String selectedShift) {
        this.selectedShift = selectedShift;
    }

    public String getLuckyDip() {
        return luckyDip;
    }

    public void setLuckyDip(String luckyDip) {
        this.luckyDip = luckyDip;
    }
}
