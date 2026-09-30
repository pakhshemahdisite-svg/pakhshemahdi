import SwiftUI

struct ProductDetailView: View {
    let product: Product
    @EnvironmentObject private var cart: CartStore

    var body: some View {
        VStack(spacing: 0) {
            ScrollView {
                VStack(alignment: .trailing, spacing: 18) {
                    ProductImageView(url: product.image).frame(height: 340).clipShape(RoundedRectangle(cornerRadius: 24))
                    Text(product.name).font(.title2.bold()).frame(maxWidth: .infinity, alignment: .trailing)
                    Text(product.shortDescription.isEmpty ? "محصول باکیفیت مناسب فروش عمده و استفاده خانگی." : product.shortDescription)
                        .frame(maxWidth: .infinity, alignment: .trailing)
                    Text(product.stockStatus == "instock" ? "● موجود در انبار" : "ناموجود")
                        .foregroundStyle(product.stockStatus == "instock" ? PMColor.success : .red)
                    Text(toman(product.price)).font(.title2.bold()).foregroundStyle(PMColor.primary).frame(maxWidth: .infinity, alignment: .trailing)
                }.padding()
            }
            Button { cart.add(product) } label: {
                Text("افزودن به سبد خرید").fontWeight(.bold).frame(maxWidth: .infinity).padding()
                    .background(PMColor.primary).foregroundStyle(.white).clipShape(RoundedRectangle(cornerRadius: 16))
            }.padding()
        }
        .background(PMColor.background)
        .navigationBarTitleDisplayMode(.inline)
    }
}
