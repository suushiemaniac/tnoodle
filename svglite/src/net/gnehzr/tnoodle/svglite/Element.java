package net.gnehzr.tnoodle.svglite;

import static net.gnehzr.tnoodle.svglite.Utils.azzert;

import java.util.ArrayList;
import java.util.HashMap;

public class Element implements Cloneable {
    
    private String tag;
    private HashMap<String, String> attributes;
    private HashMap<String, String> style;
    private ArrayList<Element> children;
    public Element(String tag) {
        this.tag = tag;
        this.children = new ArrayList<Element>();
        this.attributes = new HashMap<String, String>();
        this.style = new HashMap<String, String>();
    }

    public ArrayList<Element> getChildren() {
        return children;
    }

    public void appendChild(Element child) {
        children.add(child);
    }

    public HashMap<String, String> getAttributes() {
        return attributes;
    }

    public String getAttribute(String key) {
        azzert(key != "style");
        return attributes.get(key);
    }

    public void setAttribute(String key, String value) {
        azzert(key != "style");
        attributes.put(key, value);
    }

    public void setStyle(String key, String value) {
        style.put(key, value);
    }

    public String getStyle(String key) {
        return style.get(key);
    }

    public HashMap<String, String> getStyle() {
        return style;
    }

    private String colorToStr(Color c) {
        return c == null ? "none" : "#" + c.toHex();
    }

    public String toStyleStr() {
        StringBuilder sb = new StringBuilder();
        for(String key : style.keySet()) {
            String value = style.get(key);
            sb.append(" ").append(key).append(":").append(value).append(";");
        }
        if(sb.length() == 0) {
            return "";
        }
        return sb.substring(1);
    }

    private void addIndentation(StringBuilder sb, int level) {
        for(int i = 0; i < level; i++) {
            sb.append("\t");
        }
    }

    public void buildString(StringBuilder sb, int level) {
        addIndentation(sb, level);
        sb.append("<").append(tag);
        for(String key : attributes.keySet()) {
            String value = attributes.get(key);
            sb.append(" ");
            sb.append(key).append("=").append('"').append(value).append('"');
        }
        if(style.size() > 0) {
            sb.append(" style=\"").append(toStyleStr()).append('"');
        }
        sb.append(">");
        for(Element child : children) {
            sb.append("\n");
            child.buildString(sb, level + 1);
        }
        sb.append("\n");
        addIndentation(sb, level);
        sb.append("</").append(tag).append(">");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        buildString(sb, 0);
        return sb.toString();
    }

    private ArrayList<Element> cloneChildren() {
        ArrayList<Element> childrenCopy = new ArrayList<Element>();
        for(Element child : children) {
            childrenCopy.add((Element) child.clone());
        }
        return childrenCopy;
    }

    @SuppressWarnings("unchecked")
    public Element clone() {
        try {
            Element copy = (Element) super.clone();
            copy.attributes = (HashMap<String, String>) attributes.clone();
            copy.children = cloneChildren();
            return copy;
        } catch(java.lang.CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public void setFillColor(Color c) {
        setAttribute("fill", colorToStr(c));
    }

    public void setStrokeColor(Color c) {
        setAttribute("stroke", colorToStr(c));
    }

}
