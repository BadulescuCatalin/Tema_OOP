package task1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Account {
    Information information;
    List<Character> personaje;
    int numarJocuriJucate;

    public Account(Information information, List<Character > personaje, int numarJocuriJucate) {
        this.information = information;
        this.personaje = personaje;
        this.numarJocuriJucate = numarJocuriJucate;
    }

    @Override
    public String toString() {
        return "Account{" +
                "information=" + information +
                ", personaje=" + personaje +
                ", numarJocuriJucate=" + numarJocuriJucate +
                '}';
    }

    static class Information  { // se foloseste sablonul builder pt a instantia un obiect de tip information
        private final Credentials credentials;
        private final String nume;
        private final String tara;
        private final AlphabeticList jocuriPreferate;
        private Information(Builder builder) {
            this.credentials = builder.credentials;
            this.nume = builder.nume;
            this.tara = builder.tara;
            this.jocuriPreferate = builder.jocuriPreferate;
        }

        @Override
        public String toString() {
            return "Information{" +
                    "credentials=" + credentials +
                    ", nume='" + nume + '\'' +
                    ", tara='" + tara + '\'' +
                    ", jocuriPreferate=" + jocuriPreferate +
                    '}';
        }

        public Credentials getCredentials() {
            return  credentials;
        }
        public String getNume() {
            return nume;
        }
        public String getTara() {
            return tara;
        }
        public AlphabeticList getJocuriPreferate() {
            return jocuriPreferate;
        }

        public static class Builder {
            private final Credentials credentials;
            private final String nume;
            private String tara;
            private AlphabeticList jocuriPreferate;

            public Builder(Credentials credentials, String nume) {
                this.nume = nume;
                this.credentials = credentials;
            }
            public Builder tara(String tara) {
                this.tara = tara;
                return this;
            }
            public Builder jocuriPreferate(AlphabeticList jocuriPreferate) {
                this.jocuriPreferate = jocuriPreferate;
                return this;
            }

            public Information build() throws InvalidCommandException {

                Information information = new Information(this);
                if(information.nume == null || information.credentials == null) {
                    throw new InvalidCommandException();
                } else {
                    return information;
                }
            }
        }

    }

}
