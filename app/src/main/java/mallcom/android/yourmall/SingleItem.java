package mallcom.android.yourmall;

/**
 MIT License

 Copyright (c) 2019 Michal Youssef Issa

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.

 */

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pc on 16/07/2019.
 */

public class SingleItem implements Parcelable {
    private String marketName;
    private String imageUrl;


    public SingleItem() {

    }

    public SingleItem(String name, String imageResourceId) {
        this.marketName = name;
        this.imageUrl = imageResourceId;


    }

    protected SingleItem(Parcel in) {
        marketName = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<SingleItem> CREATOR = new Creator<SingleItem>() {
        @Override
        public SingleItem createFromParcel(Parcel in) {
            return new SingleItem(in);
        }

        @Override
        public SingleItem[] newArray(int size) {
            return new SingleItem[size];
        }
    };

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(marketName);
        dest.writeString(imageUrl);
    }
}
