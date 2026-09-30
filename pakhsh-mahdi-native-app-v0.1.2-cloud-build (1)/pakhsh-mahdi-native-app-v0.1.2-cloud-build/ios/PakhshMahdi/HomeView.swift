import SwiftUI

struct HomeView: View {
    @EnvironmentObject private var catalog: CatalogStore

    var body: some View {
        ScrollView {
            VStack(spacing: 18) {
                header
                banner
                sectionHeader("دسته‌بندی‌ها")
                ScrollView(.horizontal, showsIndicators: false) {
                    HStack(spacing: 10) {
                        ForEach(catalog.home.categories) { category in
                            VStack(spacing: 8) {
                                Image(systemName: "shippingbox.fill").font(.title2).foregroundStyle(PMColor.primary)
                                Text(category.name).font(.caption).multilineTextAlignment(.center)
                            }
                            .frame(width: 105, height: 90)
                            .background(.white)
                            .clipShape(RoundedRectangle(cornerRadius: 16))
                        }
                    }.padding(.horizontal)
                }
                sectionHeader("جدیدترین محصولات")
                ScrollView(.horizontal, showsIndicators: false) {
                    HStack(spacing: 12) {
                        ForEach(catalog.home.latestProducts) { product in
                            NavigationLink(value: product) { ProductCardView(product: product).frame(width: 220) }
                                .buttonStyle(.plain)
                        }
                    }.padding(.horizontal)
                }
            }.padding(.bottom, 24)
        }
        .background(PMColor.background)
        .ignoresSafeArea(edges: .top)
        .navigationDestination(for: Product.self) { ProductDetailView(product: $0) }
        .task { await catalog.loadHome() }
    }

    private var header: some View {
        ZStack {
            LinearGradient(colors: [PMColor.primary, PMColor.secondary], startPoint: .top, endPoint: .bottom)
            VStack(alignment: .trailing, spacing: 8) {
                Text("پخش مهدی").font(.largeTitle.bold()).foregroundStyle(.white)
                Text("تأمین‌کننده لوازم خانه و آشپزخانه").foregroundStyle(.white.opacity(0.75))
                HStack { Image(systemName: "magnifyingglass"); Text("جستجوی محصولات، برند یا دسته‌بندی...").foregroundStyle(.secondary); Spacer() }
                    .padding().background(.white).clipShape(RoundedRectangle(cornerRadius: 16))
            }.padding(.top, 58).padding(.horizontal, 18).padding(.bottom, 22)
        }
    }

    private var banner: some View {
        VStack(alignment: .trailing, spacing: 10) {
            Text("کیفیت در هر آشپزخانه").font(.title2.bold()).foregroundStyle(PMColor.primary)
            Text("انواع لوازم خانه و آشپزخانه با قیمت عمده")
            NavigationLink("مشاهده محصولات") { CatalogView() }
                .padding(.vertical, 10).padding(.horizontal, 16).background(PMColor.primary).foregroundStyle(.white).clipShape(Capsule())
        }
        .frame(maxWidth: .infinity, alignment: .trailing).padding(22).background(.white).clipShape(RoundedRectangle(cornerRadius: 24)).padding(.horizontal)
    }

    private func sectionHeader(_ title: String) -> some View {
        HStack { Spacer(); Text(title).font(.title3.bold()) }.padding(.horizontal)
    }
}
