package fr.shadou.farmenight.production.server.capability;

public interface IServeur {

    //Facture
    int getFacture();
    void setFacture(final int facture);
    void addFacture(final int add);

    //Durability
    int getDurability();
    void setDurability(final int durability);
    void addDurability(final int add);
    void sousDurability(final int sous);

}
