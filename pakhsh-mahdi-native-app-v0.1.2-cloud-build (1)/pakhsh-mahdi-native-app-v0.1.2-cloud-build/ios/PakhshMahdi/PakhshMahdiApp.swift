import SwiftUI

@main
struct PakhshMahdiApp: App {
    @StateObject private var catalog = CatalogStore()
    @StateObject private var cart = CartStore()

    var body: some Scene {
        WindowGroup {
            RootView()
                .environmentObject(catalog)
                .environmentObject(cart)
                .environment(\.layoutDirection, .rightToLeft)
        }
    }
}
