//Αλκίνοος-Μιχαήλ Μιχαλόπουλος-Τσεσμετζής.
//icsd18135.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class exercise1 {
    public static void readFromWeb(String webURL) throws IOException {
        int Drama = 0, Action = 0, Comedy = 0; //Counters για την κάθε κατηγορία.
        URL url = new URL(webURL); //Αρχικοποίηση url.
        InputStream is = url.openStream(); //Διάβασμα του Url.
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) { //Πέρασμα σε bufferedReader για να μετατραπεί σε string απο bytes.
            String line;
            while ((line = br.readLine()) != null) { //Διαβάζω τον κώδικα της σελίδας μέχρι να τελειώσει
                if (line.contains("Drama")) //Συγκρίνω τις λέξεις και αν συναντήσω keyword αυξάνω τον counter της κάθε περίπτωσεις κατα 1.
                    Drama = Drama + 1;

                else if (line.contains("Action"))
                    Action = Action + 1;

                else if (line.contains("Comedy"))
                    Comedy = Comedy + 1;
            }
            System.out.println("Dramas : " + Drama + ", Actions : " + Action + ", Comedies : " + Comedy); //Εμφανίζω.
        } catch (MalformedURLException e) { //Exceptions σε περίπτωση που δεν γίνει σύνδεση.
            e.printStackTrace();
            throw new MalformedURLException("URL is malformed!!");
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException();
        }
    }


    public static void main(String[] args) throws IOException {


                /*Χρησιμοποιώ τόσο την συνάρτηση όσο και δημιουργώ τα ζητούμενα threads για την σύγκριση που ζητήθηκε.*/
        String url1 = "https://www.imdb.com/search/title/?title_type=tv_series&release_date=2020-01-01,2021-12-31&countries=us";
        readFromWeb(url1);
        String url2 = "https://www.imdb.com/search/title/?title_type=tv_series&release_date=2019-01-01,2019-12-31";
        readFromWeb(url2);
        String url3 = "https://www.imdb.com/search/title/?title_type=tv_series&year=2018-01-01,2018-12-31";
        readFromWeb(url3);
        String url4 = "https://www.imdb.com/search/title/?title_type=tv_series&year=2017,2017&sort=moviemeter,asc";
        readFromWeb(url4);



        Thread t1 = new Thread() { //Δημιουργώ τα threads και μέσα στο σώμα του constructor γράφω την συνάρτηση run
            public void run() {    //Που ουσιαστικά είναι η λειτουργία του κάθε thread.
                try {
                    readFromWeb("https://www.imdb.com/search/title/?title_type=tv_series&release_date=2020-01-01,2021-12-31&countries=us");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };


        Thread t2 = new Thread() {
            public void run() {
                try {
                    readFromWeb("https://www.imdb.com/search/title/?title_type=tv_series&release_date=2019-01-01,2019-12-31");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t3 = new Thread() {
            public void run() {
                try {
                    readFromWeb("https://www.imdb.com/search/title/?title_type=tv_series&year=2018-01-01,2018-12-31");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t4 = new Thread() {
            public void run() {
                try {
                    readFromWeb("https://www.imdb.com/search/title/?title_type=tv_series&year=2017,2017&sort=moviemeter,asc");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        t1.start(); //Ξεκινάω τις διεργασίες των threads.
        t2.start();
        t3.start();
        t4.start();
    }
}

//5.50sec χωρίς threads.
//2.9sec με την χρήση threads.
//Eίναι λογικό να εκτελείται γρηγορότερα οποιαδήποτε διεργασία αν την χωρίσεις σε νήματα δηλαδή σε μικρότερες διεργασίες από την στιγμή
//που ο επεξεργαστής, τουλάχιστον του συστήματος μου, έχει παραπάνω threads απ' ότι τα threads του προγράμματος.
//Παρατήρησα ότι κάποιες φορές που έτρεξα το πρόγραμμα, η σειρά των threads ήταν μπερδεμένη.
