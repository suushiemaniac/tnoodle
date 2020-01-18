package org.worldcubeassociation.tnoodle.server.webscrambles.pdf


import com.itextpdf.kernel.pdf.PdfDocument
import org.worldcubeassociation.tnoodle.server.webscrambles.wcif.model.ActivityCode
import org.worldcubeassociation.tnoodle.server.webscrambles.wcif.model.ScrambleSet
import com.itextpdf.layout.Document
import java.util.*

class FmcGenericSolutionSheet(scrambleSet: ScrambleSet, activityCode: ActivityCode, competitionTitle: String, locale: Locale) : FmcSolutionSheet(scrambleSet, activityCode, competitionTitle, locale, true) {
    override fun PdfDocument.writeContents() {
        Document(this)
            .addFmcSolutionSheet(INDEX_SKIP_SCRAMBLE)
    }

    companion object {
        const val INDEX_SKIP_SCRAMBLE = -1
    }
}
