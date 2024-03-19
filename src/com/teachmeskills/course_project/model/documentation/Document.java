package com.teachmeskills.course_project.model.documentation;

public abstract sealed class Document permits Check, Invoice, Order {
    private String documentFormat;

    public String getDocumentFormat() {
        return documentFormat;
    }

    public void setDocumentFormat(String documentFormat) {
        this.documentFormat = documentFormat;
    }

    @Override
    public String toString() {
        return "Document{" +
                "documentFormat='" + documentFormat + '\'' +
                '}';
    }
}
