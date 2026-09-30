import Foundation
import SwiftUI

@MainActor
final class CatalogStore: ObservableObject {
    @Published var home = HomePayload()
    @Published var products: [Product] = []
    @Published var loading = false
    private let api = APIClient()

    func loadHome() async {
        guard home.latestProducts.isEmpty else { return }
        loading = true
        defer { loading = false }
        do { home = try await api.home() }
        catch { home = MockCatalog.home }
    }

    func loadProducts() async {
        guard products.isEmpty else { return }
        loading = true
        defer { loading = false }
        do { products = try await api.products().items }
        catch { products = MockCatalog.products }
    }
}

@MainActor
final class CartStore: ObservableObject {
    struct Line: Identifiable {
        let product: Product
        var quantity: Int
        var id: Int { product.id }
    }

    @Published var lines: [Line] = []

    func add(_ product: Product) {
        if let i = lines.firstIndex(where: { $0.product.id == product.id }) { lines[i].quantity += 1 }
        else { lines.append(Line(product: product, quantity: 1)) }
    }

    func increment(_ id: Int) { if let i = lines.firstIndex(where: { $0.id == id }) { lines[i].quantity += 1 } }
    func decrement(_ id: Int) {
        guard let i = lines.firstIndex(where: { $0.id == id }) else { return }
        if lines[i].quantity <= 1 { lines.remove(at: i) } else { lines[i].quantity -= 1 }
    }
}
