package cesar.school.code_base.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Car (val name: String, val model: String, val year: Int): Parcelable