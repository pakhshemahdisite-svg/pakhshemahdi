import SwiftUI

struct RootView: View {
    var body: some View {
        TabView {
            NavigationStack { HomeView() }
                .tabItem { Label("خانه", systemImage: "house") }
            NavigationStack { CatalogView() }
                .tabItem { Label("دسته‌بندی", systemImage: "square.grid.2x2") }
            NavigationStack { Text("علاقه‌مندی‌ها") }
                .tabItem { Label("علاقه‌مندی", systemImage: "heart") }
            NavigationStack { CartView() }
                .tabItem { Label("سبد خرید", systemImage: "cart") }
            NavigationStack { Text("پروفایل") }
                .tabItem { Label("پروفایل", systemImage: "person") }
        }
        .tint(PMColor.primary)
    }
}
