package com.teachmeskills.course_project.model.documentation;

public final class Order extends Document {
    private String name;
    private String framework;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFramework() {
        return framework;
    }

    public void setFramework(String framework) {
        this.framework = framework;
    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", framework='" + framework + '\'' +
                '}';
    }
}
