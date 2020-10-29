package control;

import model.CompactDisc;

import java.util.Arrays;

/**
 * Created by Jean-Pierre on 21.10.2016.
 */
public class CDCollectionHandler {

    private CompactDisc[][] allCDs;

    /**
     * Die Anzahl an Platzgrößen gibt die Anzahl an CD-Ständern vor - hier 4.
     * Die CD-Ständer an sich sind so groß wie die jeweilige Platzgröße.
     * @param amounts - Platzgrößen der einzelnen CD-Ständer.
     */
    public CDCollectionHandler(int[] amounts){
        //TODO: 01 - Konstruktor fertigstellen.
        allCDs = new CompactDisc [amounts.length] [];
        for (int i = 0; i < allCDs.length; i++) {
            allCDs[i] = new CompactDisc[amounts[i]];
        }
    }

    /**
     *
     * @param box - (Array Index) Gewählter CD-Ständer
     * @param place - Gewählter Platz
     * @param artist - Künstlername/Bandname
     * @param title - Albumtitel
     * @return - true, falls ein Platz frei war und die CD hinzugefügt werden konnte, sonst false.
     */
    public boolean addNewCD(int box, int place, String artist, String title){
        //TODO: 02 - Hinzufügen einer CD
        if (allCDs[box][place] == null){
            allCDs[box][place] = new CompactDisc(artist,title);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Diese Methode dient dazu, die Daten einer bestimmten Position im zweidimensionalem Array auszugeben.
     * @param box - Gewählter CD-Ständer
     * @param place - Gewählter Platz
     * @return - Entweder ein String-Array mit "Künstler" - "Titel" oder mit "Empty" - "Empty".
     */
    public String[] getInfo(int box, int place){
        String[] output = new String[2];
        //TODO: 03 - Informationen zu einer bestimmen CD
        if (allCDs[box][place] == null) {
            output[0] = "Empty";
            output[1] = "Empty";
        } else {
            output[0] = allCDs[box][place].getArtist();
            output[1] = allCDs[box][place].getTitle();
        }
        return output;
    }

    /**
     * Diese Methode dient dem Entfernen einer CD.
     * @param box - Gewählter CD-Ständer
     * @param place - Gewählter Platz
     * @return - true, falls eine vorhandene CD entfernt wurde, false, falls keine Cd zum entfernen vorhanden war.
     */
    public boolean releaseCD(int box, int place){
        //TODO: 04 - Entfernen einer bestimmten CD
        if (allCDs[box][place] == null) {
            return false;
        } else {
            allCDs[box][place] = null;
            return true;
        }
    }

    /**
     * Diese Methode dient dazu, die enthaltenen Daten aufzubereiten und als String-Array auszugeben.
     * @param index - CD-Ständer, um den es sich handelt.
     * @return Ein Array, das abwechselnd den jeweiligen Künstler und den jeweiligen Albumtitel enthält. Leere Plätze werden mit "Empty" gefüllt.
     */
    public String[] getAllCDsFrom(int index){
        //TODO: 05 - Vollständige Informationsausgabe aller CDs - Nach Fertigstellung im MainPanelHandler Zeile 165-167 entkommentieren
        String[] output = new String[allCDs[index].length *2];
        for (int i = 0; i < allCDs[index].length; i++) {
            for (int j = i*2; j < output.length; j = j + 2) {
                if (allCDs[index][i] == null) {
                    output[j] = "Empty";
                    output[j + 1] = "Empty";
                } else {
                    output[j] = allCDs[index][i].getArtist();
                    output[j + 1] = allCDs[index][i].getTitle();
                }
            }
        }
        return output;
    }

    /**
     * Diese Methode dient dazu, einen CD-Ständer zu komprimieren. Dabei rücken spätere CDs einfach auf. Die vorhandene Sortierung bleibt erhalten.
     * @param box - Gewählter CD-Ständer
     */
    public void pack(int box){
        //TODO: 06 - Komprimieren eines CD-Ständers, von unten nach oben
        boolean cd = true;
        for(int i = 0; i < allCDs[box].length && cd; i++){
            if(allCDs[box][i] == null){
                cd = false;
                for(int j = i+1; j < allCDs[box].length && !cd; j++){
                    if(allCDs[box][j] != null){
                        allCDs[box][i] = allCDs[box][j];
                        allCDs[box][j] = null;
                        cd = true;
                    }
                }
            }
        }
    }

    /**
     * Diese Methode dient dazu, einen CD-Ständer zu sortieren nach Artist+Title. Gleichzeitig wird der CD-Ständer komprimiert.
     * @param box - Gewählter CD-Ständer
     */
    public void sort(int box){
        //(TODO: 07 - Sortieren eines CD-Ständers)
        pack(box);
        int noNullCDs = 0;
        for (int i = 0; i < allCDs[box].length && allCDs[box][i] != null; i++) {
            noNullCDs = i + 1;
        }
        String[] sorted = new String[noNullCDs];
        for (int j = 0; j < noNullCDs; j++) {
            sorted[j] = allCDs[box][j].getArtist() + allCDs[box][j].getTitle();
        }
        Arrays.sort(sorted);
        System.out.println(Arrays.toString(sorted));
        for (int k = 0; k < noNullCDs; k++) {
            allCDs[box][k].setArtist(sorted[k]);

        }

    }
}
