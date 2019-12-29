package mallcom.android.yourmall;

import android.os.Parcel;
import android.os.Parcelable;

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

/**
 * Created by pc on 09/12/2018.
 */

public class Word implements Parcelable {
    private String marketName;
    private String goodsType;
    private String location;
    private String imageUrl;


    /** Constant value that represents no image was provided for this word */
    private static final int NO_IMAGE_PROVIDED = -1;

    public Word(){

    }



    public Word(String marketName, String goodsType, String location, String imageUrl, int priority) {
        this.marketName = marketName;
        this.goodsType = goodsType;
        this.location = location;
        this.imageUrl = imageUrl;





    }


    protected Word(Parcel in) {
        marketName = in.readString();
        goodsType = in.readString();
        location = in.readString();
        imageUrl = in.readString();
    }


    public String getMarketName() {
        return marketName;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getLocation() {
        return location;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(marketName);
        dest.writeString(goodsType);
        dest.writeString(location);
        dest.writeString(imageUrl);
    }

    public static final Parcelable.Creator<Word> CREATOR = new Parcelable.Creator<Word>() {
        @Override
        public Word createFromParcel(Parcel source) {
            return new Word(source);
        }

        @Override
        public Word[] newArray(int size) {
            return new Word[size];
        }
    };
}

