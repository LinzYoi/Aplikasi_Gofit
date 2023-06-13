import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gofit.databinding.RvPresensiBookingKelasMemberBinding
import com.example.gofit.model.presensiBookingKelas.DataItemGetPresensiBookingKelasByIdMember
import com.example.gofit.model.presensiBookingKelas.PresensiBookingKelasItem

class PresensiBookingKelasMemberAdapter(private val item: List<PresensiBookingKelasItem>) : RecyclerView.Adapter<PresensiBookingKelasMemberAdapter.ViewHolder>() {

    private var itemClickListener: OnItemClickListener? = null

    inner class ViewHolder(val binding: RvPresensiBookingKelasMemberBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PresensiBookingKelasItem) {
            with(binding) {
                // Setel data dari presensiBookingKelas
                tvNamaKelas.text = data.jadwalHarianForeignKey.jadwalUmumForeignKey.kelasForeignKey.namaKelas
                tvTanggalBooking.text = data.tanggalBooking
                tvTanggalYangDibooking.text = data.tanggalYangDibooking
                tvNamaInstruktur.text = data.jadwalHarianForeignKey.instrukturForeignKey.namaInstruktur
                tvHari.text = data.jadwalHarianForeignKey.jadwalUmumForeignKey.hari
                tvJam.text = data.jadwalHarianForeignKey.jadwalUmumForeignKey.jam

                // Tambahkan pengaturan data depositKelasMember jika diperlukan

                // Setel onClickListener untuk tombol delete
                binding.btnDeleteBookingKelas.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val clickedItem = item[position]
                        itemClickListener?.onDeleteButtonClick(clickedItem)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvPresensiBookingKelasMemberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = item[position]
        holder.bind(data)
    }

    fun setOnDeleteButtonClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    interface OnItemClickListener {
        fun onDeleteButtonClick(data: PresensiBookingKelasItem)
    }
}
