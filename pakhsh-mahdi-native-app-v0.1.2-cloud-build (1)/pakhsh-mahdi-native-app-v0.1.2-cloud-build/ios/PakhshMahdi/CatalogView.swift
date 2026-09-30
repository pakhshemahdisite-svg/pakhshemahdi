import SwiftUI

struct CatalogView: View {
    @EnvironmentObject private var catalog: CatalogStore
    private let columns = [GridItem(.flexible(), spacing: 10), GridItem(.flexible(), spacing: 10)]

    var body: some View {
        ScrollView {
            VStack(alignment: .trailing, spacing: 12) {
                Text("لوازم آشپزخانه").font(.title.bold())
                Text("محصولات عمده پخش مهدی").foregroundStyle(.secondary)
                HStack { Button("فیلترها") {}; Button("جستجو") {}; Spacer() }
                LazyVGrid(columns: columns, spacing: 10) {
                    ForEach(catalog.products) { product in
                        NavigationLink(value: product) { ProductCardView(product: product) }.buttonStyle(.plain)
                    }
                }
            }.padding()
        }
        .background(PMColor.background)
        .navigationDestination(for: Product.self) { ProductDetailView(product: $0) }
        .task { await catalog.loadProducts() }
    }
}
