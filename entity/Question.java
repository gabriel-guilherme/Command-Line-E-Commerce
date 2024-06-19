package entity;

import java.util.List;

public class Question extends Entity {
    private String description;
    private boolean isObjective;
    private List<String> alternatives;
    private String response = "";
    private float value;
    private float grade = 0;

    /// CONSTRUCTOR
    public Question(String description, boolean isObjective, float value, int id) {
        this.setDescription(description);
        this.setType(isObjective);
        this.setValue(value);
        this.setId(id);
    }

    public Question(String description, boolean isObjective, List<String> alternatives, float value, int id) {
        this.setDescription(description);
        this.setType(isObjective);
        this.setValue(value);
        this.setAlternatives(alternatives);
        this.setId(id);
    }

    /// GETTERS

    public float getGrade() {
        return this.grade;
    }

    public float getValue() {
        return this.value;
    }

    public String getResponse() {
        return this.response;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getType() {
        return this.isObjective;
    }

    public List<String> getAlternatives() {
        return this.alternatives;
    }

    /// SETTERS

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(boolean isObjective) {
        this.isObjective = isObjective;
    }

    public void setAlternatives(List<String> alternatives) {
        this.alternatives = alternatives;
    }

    @Override
    public String toString() {

        if (this.getType()) {
            StringBuilder formatedAlternatives = new StringBuilder();
            for (int i = 0; i < getAlternatives().size(); i++) {
                formatedAlternatives.append(i + 1).append(". ").append(getAlternatives().get(i)).append("\n");
            }

            return getDescription() + "\n\n" + formatedAlternatives.toString() + "\n";
        } else {
            return getDescription();
        }

    }
}