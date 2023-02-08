package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.ArtistsManager;
import ba.unsa.etf.rpr.business.ArtworkManager;
import ba.unsa.etf.rpr.business.ExhibitionManager;
import ba.unsa.etf.rpr.domain.Artists;
import ba.unsa.etf.rpr.domain.Artwork;
import org.apache.commons.cli.*;
import java.io.PrintWriter;
import java.util.*;


/**
 * @author Amina Hromic
 * CLI (Command Line Interface) implementation App class
 */
public class App {
    /**
     * final variables describing all available options
     */
    private static final Option addArtist = new Option("aa","add-artist",false, "Adding a new artist to the art gallery database");
    private static final Option getExhibitions = new Option("ge","get-exhibition",false, "Printing all exhibitions in the art gallery database");
    private static final Option getArtists = new Option("ga", "get-artists",false, "Printing all artists from the art gallery database");
    private static final Option getPaintings = new Option("gp", "get-paintings",false, "Printing all paintings from the art gallery database");



    /**
     * @param options
     */
    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar Project.jar [option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }

    /**
     * adding options
     * @return Options
     */

    public static Options addOptions() {
        Options options = new Options();
        options.addOption(addArtist);
        options.addOption(getExhibitions);
        options.addOption(getArtists);
        options.addOption(getPaintings);
        return options;
    }

    /**
     * method which searches through all the Artworjk
     * @param listOfPaintings
     * @param name
     * @return Artwork
     */

    public static Artwork searchThroughArtwork(List<Artwork> listOfPaintings, String name) {

        Artwork paintings = null;
        paintings = listOfPaintings.stream().filter(exh -> exh.getName().toLowerCase().equals(name.toLowerCase())).findAny().get();
        return paintings;

    }


    /**
     *
     * @param args
     * @throws Exception when input is out of order
     */

    public static void main(String[] args) throws Exception {
        Options options = addOptions();

        CommandLineParser commandLineParser = new DefaultParser();

        CommandLine cl = commandLineParser.parse(options, args);

        if((cl.hasOption(addArtist.getOpt()) || cl.hasOption(addArtist.getLongOpt())) ){
            try {
                ArtistsManager artistsManager = new ArtistsManager();

                Artists artist = new Artists();
                artist.setName(cl.getArgList().get(0));

                artistsManager.add(artist);
                System.out.println("You successfully added a new artist to a database!");
            }
            catch(Exception e) {
                System.out.println("There is already an artist with same name in the database! Please try again.");
                System.exit(1);
            }

        }
        else if(cl.hasOption(getExhibitions.getOpt()) || cl.hasOption(getExhibitions.getLongOpt())){
           ExhibitionManager exhibitionsManager = new ExhibitionManager();
            exhibitionsManager.getAll().forEach(q -> System.out.println(q.getExhibition_name()));

        }
        else if(cl.hasOption(getPaintings.getOpt()) || cl.hasOption(getPaintings.getLongOpt())){
            ArtworkManager artworkManager = new ArtworkManager();
           artworkManager.getAll().forEach(q -> System.out.println(q.getName()));

        }
        else if(cl.hasOption(getArtists.getOpt()) || cl.hasOption(getArtists.getLongOpt())){
            ArtistsManager artistsManager = new ArtistsManager();
            artistsManager.getAll().forEach(q -> System.out.println(q.getName()));

        } else {
            printFormattedOptions(options);
            System.exit(-1);

        }

    }
}