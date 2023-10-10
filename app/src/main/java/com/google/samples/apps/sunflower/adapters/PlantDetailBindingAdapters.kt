/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.sunflower.adapters

import android.text.method.LinkMovementMethod
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.samples.apps.sunflower.R
/*
 * Bu bağlama adaptörü, bir ImageView (resim görüntüleyici) nesnesine bir URL'den resim yüklemek için kullanılır.
 * Eğer imageUrl boş veya null ise, işlem yapılmaz. Aksi takdirde, Glide kütüphanesi kullanılarak resim yüklenir ve görünüme yerleştirilir.
 */
@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (imageUrl.isNullOrEmpty()) {
        return
    }
    Glide.with(view)
        .load(imageUrl)
        .placeholder(android.R.drawable.progress_indeterminate_horizontal)
        .into(view)
}

/*
 * Bu bağlama adaptörü, bir FloatingActionButton (kaydırılabilir düğme)
 *  nesnesinin görünürlüğünü kontrol etmek için kullanılır.
 * Eğer "isGone" değeri null veya true ise, düğme gizlenir (hide).
 * Aksi durumda, düğme gösterilir (show).
 */
@BindingAdapter("isFabGone")
fun bindIsFabGone(view: FloatingActionButton, isGone: Boolean?) {
    if (isGone == null || isGone) {
        view.hide()
    } else {
        view.show()
    }
}

/*
 * Bu bağlama adaptörü, bir TextView (metin görüntüleyici) nesnesine HTML biçimli metni görüntülemek için kullanılır.
 * "description" metni HTML biçiminde değilse, TextView boş olarak ayarlanır. Aksi takdirde, HtmlCompat.fromHtml ile metin HTML'den düzeltilir ve görüntülenir.
 * Ayrıca, metindeki bağlantıları tıklanabilir yapmak için LinkMovementMethod ayarlanır.
 */
@BindingAdapter("renderHtml")
fun bindRenderHtml(view: TextView, description: String?) {
    if (description != null) {
        view.text = HtmlCompat.fromHtml(description, FROM_HTML_MODE_COMPACT)
        view.movementMethod = LinkMovementMethod.getInstance()
    } else {
        view.text = ""
    }
}

/*
 * Bu bağlama adaptörü, bitkinin sulama aralığını hesaplar ve görüntülemek için kullanılır.
 * "wateringInterval", bitkinin sulama aralığını temsil eder ve R.plurals.watering_needs_suffix kaynağını kullanarak metni oluşturur.
 */
@BindingAdapter("wateringText")
fun bindWateringText(textView: TextView, wateringInterval: Int) {
    val resources = textView.context.resources
    val quantityString = resources.getQuantityString(
        R.plurals.watering_needs_suffix,
        wateringInterval,
        wateringInterval
    )

    textView.text = quantityString
}

