package fr.shadou.farmenight;

public class Gestion {

    public int TickTime(int seconde){
        return seconde*20;
    }
    public int TickTime(int seconde, int minute){
        return TickTime(seconde+minute*60);
    }
    public int TickTime(int seconde,int minute, int hour){
        return TickTime(seconde, minute+hour*60);
    }

    public int Pourcen(int unity, int total){
        return unity*100/total;
    }

    public int PourcentUnity(int total){
        return total/100;
    }

}
