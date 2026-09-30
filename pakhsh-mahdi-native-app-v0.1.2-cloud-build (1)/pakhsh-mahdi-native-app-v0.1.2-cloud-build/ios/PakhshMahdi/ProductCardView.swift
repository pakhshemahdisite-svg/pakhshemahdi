import SwiftUI

struct ProductCardView: View {
    let product: Product
    @EnvironmentObject private var cart: CartStore

    var body: some View {
        VStack(alignment: .trailing, spacing: 10) {
            ProductImageView(url: product.image)
                .frame(height: 150)
                .clipShape(RoundedRectangle(cornerRadius: 16))
            Text(product.name).font(.headline).lineLimit(2).frame(maxWidth: .infinity, alignment: .trailing)
            HStack {
                Button { cart.add(product) } label: {
                    Image(systemName: "cart.badge.plus").foregroundStyle(.white).padding(10).background(PMColor.primary).clipShape(Circle())
                }
                Spacer()
                Text(toman(product.price)).fontWeight(.bold).foregroundStyle(PMColor.primary)
            }
        }
        .padding(10)
        .background(.white)
        .clipShape(RoundedRectangle(cornerRadius: 20))
    }
}

func toman(_ value: String) -> String {
    let int = Int(Double(value) ?? 0)
    let formatter = NumberFormatter(); formatter.numberStyle = .decimal
    return "\(formatter.string(from: NSNumber(value: int)) ?? value) تومان"
}
