package cesar.school.code_base.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(val name: String, val photo: Int): Parcelable