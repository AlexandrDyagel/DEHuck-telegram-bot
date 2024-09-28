package org.example

import io.github.dehuckakpyt.telegrambot.config.TelegramBotConfig
import io.github.dehuckakpyt.telegrambot.factory.TelegramBotFactory
import org.example.handlers.*
import org.example.utils.Config


fun main() {
    val config = TelegramBotConfig().apply {

        token = Config.BOT_TOKEN
        username = Config.BOT_USERNAME

        receiving {
            handling {
                startCommand()
                callbackCommand()
                forwardCommand()
                sendMessages()
                linkMessage()
                addChannel()
                checkMemberChannel()
            }
        }
    }

    val context = TelegramBotFactory.createTelegramBotContext(config)
    val bot = context.telegramBot
    val updateReceiver = context.updateReceiver

    updateReceiver.start()
    readlnOrNull()
    updateReceiver.stop()
}
/*

private fun test() {
    val stop1 = Stop(
        id = 1,
        name = "Северная",
        latitude = 40.712776,
        longitude = -74.005974,
        busArrivals = listOf(
            BusArrival(routeId = 1, routeName = "Route 1", arrivalTime = "07:24"),
            BusArrival(routeId = 1, routeName = "Route 1", arrivalTime = "08:22"),
            BusArrival(routeId = 1, routeName = "Route 1", arrivalTime = "09:20"),
            BusArrival(routeId = 1, routeName = "Route 1", arrivalTime = "10:18"),
            BusArrival(routeId = 1, routeName = "Route 1", arrivalTime = "11:55"),
            BusArrival(routeId = 1, routeName = "Route 1", arrivalTime = "12:53"),
            BusArrival(routeId = 1, routeName = "Route 1", arrivalTime = "13:51"),
            BusArrival(routeId = 1, routeName = "Route 1", arrivalTime = "14:49"),
            BusArrival(routeId = 1, routeName = "Route 1", arrivalTime = "15:47"),
            BusArrival(routeId = 1, routeName = "Route 1", arrivalTime = "17:59"),
            BusArrival(routeId = 1, routeName = "Route 1", arrivalTime = "18:57"),
            BusArrival(routeId = 2, routeName = "Route 2", arrivalTime = "18:59"),
        )
    )
//
    val stop2 = Stop(
        id = 2,
        name = "Больница",
        latitude = 40.713776,
        longitude = -74.006974,
        busArrivals = listOf(
            BusArrival(routeId = 1, routeName = "Route 1", arrivalTime = "07:26"),
            BusArrival(routeId = 1, routeName = "Route 1", arrivalTime = "08:24"),
            BusArrival(routeId = 1, routeName = "Route 1", arrivalTime = "09:22"),
            BusArrival(routeId = 1, routeName = "Route 1", arrivalTime = "10:20"),
            BusArrival(routeId = 1, routeName = "Route 1", arrivalTime = "11:57"),
            BusArrival(routeId = 1, routeName = "Route 1", arrivalTime = "12:55"),
            BusArrival(routeId = 1, routeName = "Route 1", arrivalTime = "13:53"),
            BusArrival(routeId = 1, routeName = "Route 1", arrivalTime = "14:51"),
            BusArrival(routeId = 1, routeName = "Route 1", arrivalTime = "15:49"),
            BusArrival(routeId = 1, routeName = "Route 1", arrivalTime = "18:01"),
            BusArrival(routeId = 1, routeName = "Route 1", arrivalTime = "18:59"),
            BusArrival(routeId = 2, routeName = "Route 2", arrivalTime = "14:59"),
        )
    )
    val stops = listOf(stop1, stop2)
// Пример данных для маршрута
    val route = Route(
        id = 1,
        number = 1,
        name = "Больница - Пивзавод",
        stops = listOf(stop1, stop2)
    )
    val route2 = Route(
        id = 2,
        number = 1,
        name = "Пивзавод - Больница",
        stops = listOf(stop1, stop2)
    )
    val routes = listOf(route, route2)
    routes.forEach { router ->
        println("№${router.number}${router.name}")
        router.stops.forEach { stop ->
            println(stop.name)
            stop.busArrivals.filter { it.routeId == router.id }.forEach { busArrival ->
                print("${busArrival.arrivalTime} ")
            }
            println()
        }
    }
    println()
    val severalStop = stops.first { it.name == "Северная" }
    println(severalStop.name)

    val routeIds = severalStop.busArrivals.groupBy { it.routeId }.forEach { routeId, busArrivals ->
        routes.firstOrNull { it.id == routeId }?.let { route ->
            println(route.name)
        }
    }
}

// Data class for bus arrival information
data class BusArrival(
    val routeId: Int,
    val routeName: String,
    val arrivalTime: String // Можно использовать LocalTime или другой подходящий тип данных для времени
)

// Data class for a bus stop
data class Stop(
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val busArrivals: List<BusArrival>
)

// Data class for a route
data class Route(
    val id: Int,
    val number: Int,
    val name: String,
    val stops: List<Stop>
)
*/
