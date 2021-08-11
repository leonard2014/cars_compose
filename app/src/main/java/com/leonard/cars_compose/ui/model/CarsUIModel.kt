package com.leonard.cars_compose.ui.model

import com.leonard.cars_compose.domain.model.Cars

typealias CarsUIList = List<CarsUIItem>

data class CarsUIItem(
    val title: String,
    val price: String,
    val image: String?
)

sealed class UIState {
    object Loading : UIState()
    class Content(val list: CarsUIList) : UIState()
    object Empty : UIState()
    object Error : UIState()
}

fun Cars.toUIModel(): CarsUIList {
    return map {
        CarsUIItem(
            title = "${it.year} ${it.manufacturer} ${it.make}",
            price = "$${it.price}",
            image = it.image
        )
    }
}

fun Result<Cars>.toUIModel(): UIState {
    return if (isSuccess) {
        getOrNull()?.toUIModel()?.let {
            if (it.isNotEmpty()) UIState.Content(it) else UIState.Empty
        } ?: UIState.Empty
    } else {
        UIState.Error
    }
}



