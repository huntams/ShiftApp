package com.huntams.data.mappers

import com.huntams.model.Coordinate
import com.huntams.model.Date
import com.huntams.model.InfoPage
import com.huntams.model.Location
import com.huntams.model.Picture
import com.huntams.model.Street
import com.huntams.model.User
import com.huntams.model.UserId
import com.huntams.model.UserName
import com.huntams.network.model.ApiCoordinate
import com.huntams.network.model.ApiDate
import com.huntams.network.model.ApiId
import com.huntams.network.model.ApiInfoPage
import com.huntams.network.model.ApiLocation
import com.huntams.network.model.ApiPicture
import com.huntams.network.model.ApiStreet
import com.huntams.network.model.ApiUser
import com.huntams.network.model.ApiUserName
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserMapper @Inject constructor() {
    fun modelToApi(user: User) = ApiUser(
        name = ApiUserName(
            title = user.name.title,
            first = user.name.first,
            last = user.name.last,
        ),
        location = ApiLocation(
            street = ApiStreet(
                number = user.location.street.number,
                name = user.location.street.name
            ),
            coordinates = ApiCoordinate(
                latitude = user.location.coordinates.latitude,
                longitude = user.location.coordinates.longitude
            ),
            city = user.location.city,
            state = user.location.state,
            country = user.location.country
        ),
        email = user.email,
        dob = ApiDate(date = user.dob.date, age = user.dob.age),
        phone = user.phone,
        cell = user.cell,
        picture = ApiPicture(
            large = user.picture.large,
            medium = user.picture.medium,
            thumbnail = user.picture.thumbnail
        ),
        id = ApiId(
            value = user.userId.value
        )
    )
    fun apiToModel(apiUser: ApiUser, apiInfoPage: ApiInfoPage) = User(
        name = UserName(
            title = apiUser.name.title,
            first = apiUser.name.first,
            last = apiUser.name.last,
        ),
        location = Location(
            street = Street(
                number = apiUser.location.street.number,
                name = apiUser.location.street.name
            ),
            coordinates = Coordinate(
                latitude = apiUser.location.coordinates.latitude,
                longitude = apiUser.location.coordinates.longitude
            ),
            city = apiUser.location.city,
            state = apiUser.location.state,
            country = apiUser.location.country
        ),
        email = apiUser.email,
        dob = Date(date = apiUser.dob.date, age = apiUser.dob.age),
        phone = apiUser.phone,
        cell = apiUser.cell,
        picture = Picture(
            large = apiUser.picture.large,
            medium = apiUser.picture.medium,
            thumbnail = apiUser.picture.thumbnail
        ),
        userId = UserId(
            apiUser.id.value?:"null"
        ),
        infoPage = InfoPage(
            seed = apiInfoPage.seed,
            results = apiInfoPage.results,
            page = apiInfoPage.page,
            version = apiInfoPage.version
        )
    )
}