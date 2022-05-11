package tk.swapjob.dao.requests;

import tk.swapjob.model.Preference;
import tk.swapjob.model.Skill;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PythonRequest implements Serializable {
    public static class User {
        private List<Float> distance;
        private List<Float> salary;
        private List<Float> labour;
        private Float remote;
        private List<String> skills;
        private Integer pc;

        public User() {
        }

        public User(tk.swapjob.model.User user) {

            List<Float> distance = new ArrayList<>();
            distance.add(Float.MIN_VALUE);
            distance.add(Float.MAX_VALUE);
            List<Float> salary = new ArrayList<>();
            salary.add(Float.MIN_VALUE);
            salary.add(Float.MAX_VALUE);
            List<Float> labour = new ArrayList<>();
            labour.add(Float.MIN_VALUE);
            labour.add(Float.MAX_VALUE);
            Float remote = 0.5f;

            for (Preference preference : user.getPreferenceList()) {
                if (preference.getTitle().equals("distance")) {
                    distance = new ArrayList<>();
                    distance.add(preference.getLowThreshold());
                    distance.add(preference.getHighThreshold());
                }
                if (preference.getTitle().equals("salary")) {
                    salary = new ArrayList<>();
                    salary.add(preference.getLowThreshold());
                    salary.add(preference.getHighThreshold());
                }
                if (preference.getTitle().equals("labour")) {
                    labour = new ArrayList<>();
                    labour.add(preference.getLowThreshold());
                    labour.add(preference.getHighThreshold());
                }
                if (preference.getTitle().equals("remote")) {
                    remote = preference.getValue();
                }
            }

            this.distance = distance;
            this.salary = salary;
            this.labour = labour;
            this.remote = remote;

            this.skills = new ArrayList<>();
            for (Skill skill : user.getSkillList()) {
                this.skills.add(skill.getTitle());
            }
            this.pc = user.getPostalCode();
        }

        public List<Float> getDistance() {
            return distance;
        }

        public void setDistance(List<Float> distance) {
            this.distance = distance;
        }

        public List<Float> getSalary() {
            return salary;
        }

        public void setSalary(List<Float> salary) {
            this.salary = salary;
        }

        public List<Float> getLabour() {
            return labour;
        }

        public void setLabour(List<Float> labour) {
            this.labour = labour;
        }

        public Float getRemote() {
            return remote;
        }

        public void setRemote(Float remote) {
            this.remote = remote;
        }

        public List<String> getSkills() {
            return skills;
        }

        public void setSkills(List<String> skills) {
            this.skills = skills;
        }

        public Integer getPc() {
            return pc;
        }

        public void setPc(Integer pc) {
            this.pc = pc;
        }
    }

    public static class Offer {
        private Long id;
        private boolean remote;
        private Float salary;
        private Integer labour;
        private List<String> skills;
        private List<Float> coords;

        public Offer() {
        }

        public Offer(tk.swapjob.model.Offer offer) {
            this.id = offer.getId();
            this.remote = offer.getRemote();
            this.salary = offer.getSalary();
            this.labour = offer.getLabour();
            this.skills = new ArrayList<>();
            for (Skill skill : offer.getSkillList()) {
                this.skills.add(skill.getTitle());
            }

            String lat = offer.getCompany().getCoordinates().split(",")[0].replace("(", "");
            String lang = offer.getCompany().getCoordinates().split(",")[1].replace(")", "");
            this.coords = new ArrayList<>();
            this.coords.add(Float.parseFloat(lat));
            this.coords.add(Float.parseFloat(lang));

        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public boolean isRemote() {
            return remote;
        }

        public void setRemote(boolean remote) {
            this.remote = remote;
        }

        public Float getSalary() {
            return salary;
        }

        public void setSalary(Float salary) {
            this.salary = salary;
        }

        public Integer getLabour() {
            return labour;
        }

        public void setLabour(Integer labour) {
            this.labour = labour;
        }

        public List<String> getSkills() {
            return skills;
        }

        public void setSkills(List<String> skills) {
            this.skills = skills;
        }

        public List<Float> getCoords() {
            return coords;
        }

        public void setCoords(List<Float> coords) {
            this.coords = coords;
        }
    }

    private User user;
    private List<Offer> offers;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}
