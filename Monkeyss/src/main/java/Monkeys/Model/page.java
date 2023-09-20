package Monkeys.Model;

public class page {
    String titel;
    String imgLink1;
    String text1;
    String imgLink2;
    String text2;
    String imgLink3;
    String text3;
    int id;

    public page(int id, String titel, String imgLink1, String text1) {
        setImgLink1(imgLink1);
        setImgLink2(imgLink2);
        setImgLink3(imgLink3);
        setText1(text1);
        setText2(text2);
        setText3(text3);
        setTitel(titel);
        setId(id);

    }

    public void setImgLink1(String imgLink1) {
        this.imgLink1 = imgLink1;
    }

    public void setImgLink2(String imgLink2) {
        this.imgLink2 = imgLink2;
    }
    public void setImgLink3(String imgLink3) {
        this.imgLink3 = imgLink3;
    }
    public void setText1(String text1) {
        this.text1 = text1;
    }
    public void setText2(String text2) {
        this.text2 = text2;
    }
    public void setText3(String text3) {
        this.text3 = text3;
    }
    public void setTitel(String titel) {
        this.titel = titel;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getImgLink1() {
        return imgLink1;
    }
    public String getImgLink2() {
        return imgLink2;
    }
    public String getImgLink3() {
        return imgLink3;
    }
    public String getText1() {
        return text1;
    }
    public String getText2() {
        return text2;
    }
    public String getText3() {
        return text3;
    }
    public String getTitel() {
        return titel;
    }

}
