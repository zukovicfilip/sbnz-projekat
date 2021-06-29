package sbnz.integracija.example.controller.dtos;

public class AdviceDTO {

    String priceAdvice;

    public AdviceDTO(String priceAdvice) {
        this.priceAdvice = priceAdvice;
    }

    public AdviceDTO() {
    }

    public String getPriceAdvice() {
        return priceAdvice;
    }

    public void setPriceAdvice(String priceAdvice) {
        this.priceAdvice = priceAdvice;
    }
}
