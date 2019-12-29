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

public class Item implements Parcelable {
    private String description;
    private String price;
    private String itemType;
    private String imageUrl;

    public Item(){

    }



    public Item(String description, String price, String itemType, String imageUrl) {
        this.description = description;
        this.price = price;
        this.itemType = itemType;
        this.imageUrl = imageUrl;

    }

    protected Item(Parcel in) {
        description = in.readString();
        price = in.readString();
        itemType = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getItemType() {
        return itemType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeString(price);
        dest.writeString(itemType);
        dest.writeString(imageUrl);
    }
}
