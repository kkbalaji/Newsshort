package nri.startup.inshort.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Krushnakant Solanki at NRI on 17-02-2016.
 */
public class NewsModel extends RealmObject implements Parcelable {

    public static final Parcelable.Creator<NewsModel> CREATOR = new Parcelable.Creator<NewsModel>() {
        @Override
        public NewsModel createFromParcel(Parcel source) {
            return new NewsModel(source);
        }

        @Override
        public NewsModel[] newArray(int size) {
            return new NewsModel[size];
        }
    };
    int id;
    String title;
    String description;
    String category;
    String image;
    @SerializedName("added_date")
    String addedDate;
    String status;
    @SerializedName("redirect_link")
    String redirectLink;
    String writer;

    public NewsModel() {
    }

    protected NewsModel(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.category = in.readString();
        this.image = in.readString();
        this.addedDate = in.readString();
        this.status = in.readString();
        this.redirectLink = in.readString();
        this.writer = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRedirectLink() {
        return redirectLink;
    }

    public void setRedirectLink(String redirectLink) {
        this.redirectLink = redirectLink;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.category);
        dest.writeString(this.image);
        dest.writeString(this.addedDate);
        dest.writeString(this.status);
        dest.writeString(this.redirectLink);
        dest.writeString(this.writer);
    }
}
